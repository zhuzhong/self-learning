/**
 * 
 */
package com.zz.learning.cache.extd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunff
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCacheable {

	
	int time() default 10; //  单位分钟，但是在有的cacheManager中不支持，这个扩展为了支持redis
	/**
	 * Name of the caches in which the update takes place.
	 * <p>May be used to determine the target cache (or caches), matching the
	 * qualifier value (or the bean name(s)) of (a) specific bean definition.
	 */
	String[] value();

	/**
	 * Spring Expression Language (SpEL) attribute for computing the key dynamically.
	 * <p>Default is "", meaning all method parameters are considered as a key.
	 */
	String key() default "";

	/**
	 * Spring Expression Language (SpEL) attribute used for conditioning the method caching.
	 * <p>Default is "", meaning the method is always cached.
	 */
	String condition() default "";

	/**
	 * Spring Expression Language (SpEL) attribute used to veto method caching.
	 * <p>Unlike {@link #condition()}, this expression is evaluated after the method
	 * has been called and can therefore refer to the {@code result}. Default is "",
	 * meaning that caching is never vetoed.
	 * @since 3.2
	 */
	String unless() default "";
}
