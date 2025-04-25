package com.joaowudarski.usecase.impl;

import com.joaowudarski.media.AbstractMedia;
import com.joaowudarski.usecase.UpdateMediaRegister;
import com.joaowudarski.exception.ObjectUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class UpdateMediaRegisterImpl implements UpdateMediaRegister {

    private final ApplicationContext applicationContext;

    @Override
    public String execute(AbstractMedia actualMedia, AbstractMedia newMedia) {
        updateIgnoringNulls(actualMedia, newMedia);

        Class<?> repositoryClass = actualMedia.getMediaType().getRepository();
        JpaRepository<AbstractMedia, String> repository =
                (JpaRepository<AbstractMedia, String>) applicationContext.getBean(repositoryClass);

        return repository.save(actualMedia).getId();
    }

    private void updateIgnoringNulls(Object target, Object source) {
        validateObjects(target, source);
        updateFields(target, source);
    }

    private void validateObjects(Object target, Object source) {
        if (isNull(target) || isNull(source)) {
            throw new ObjectUpdateException("Objects cannot be null.");
        }

        if (!target.getClass().equals(source.getClass())) {
            throw new ObjectUpdateException("Objects must be of the same type.");
        }
    }

    private void updateFields(Object target, Object source) {
        Class<?> clazz = target.getClass();
        while (clazz != null) {
            updateClassFields(target, source, clazz);
            clazz = clazz.getSuperclass();
        }
    }

    private void updateClassFields(Object target, Object source, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            try {
                String fieldName = field.getName();
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Method getter = clazz.getMethod(getterName);
                Method setter = clazz.getMethod(setterName, field.getType());

                Object sourceValue = getter.invoke(source);
                if (nonNull(sourceValue)) {
                    setter.invoke(target, sourceValue);
                }
            } catch (NoSuchMethodException e) {
                // Skip fields without getters/setters
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new ObjectUpdateException("Error accessing field: " + field.getName(), e);
            }
        }
    }
}
