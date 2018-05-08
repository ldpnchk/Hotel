package ua.edu.controller.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ua.edu.entity.UserRole;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAllowed {

	UserRole[] roles();
	
}
