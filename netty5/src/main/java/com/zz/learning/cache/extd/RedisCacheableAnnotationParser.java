/**
 * 
 */
package com.zz.learning.cache.extd;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.annotation.CacheAnnotationParser;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;
import org.springframework.cache.interceptor.CacheOperation;

/**
 * 与SpringCacheAnnotationParser 一起  注入
 * org.springframework.cache.annotation.AnnotationCacheOperationSource
 * 构建器
 * @author sunff
 *
 */
public class RedisCacheableAnnotationParser implements CacheAnnotationParser, Serializable {

	@Override
	public Collection<CacheOperation> parseCacheAnnotations(AnnotatedElement ae) {
		Collection<CacheOperation> ops = null;

		Collection<RedisCacheable> cacheables = getAnnotations(ae, RedisCacheable.class);
		if (cacheables != null) {
			ops = lazyInit(ops);
			for (RedisCacheable cacheable : cacheables) {
				ops.add(parseRedisCacheableAnnotation(ae, cacheable));
			}
		}
		

		return ops;
	}

	private <T extends Annotation> Collection<CacheOperation> lazyInit(Collection<CacheOperation> ops) {
		return (ops != null ? ops : new ArrayList<CacheOperation>(1));
	}

	RedisCacheableOperation parseRedisCacheableAnnotation(AnnotatedElement ae, RedisCacheable caching) {
		RedisCacheableOperation op = new RedisCacheableOperation();
		op.setCacheNames(caching.value());
		op.setCondition(caching.condition());
		op.setUnless(caching.unless());
		op.setKey(caching.key());
		op.setTime(caching.time());
		op.setName(ae.toString());
		
		return op;
	}

	

	

	private <T extends Annotation> Collection<T> getAnnotations(AnnotatedElement ae, Class<T> annotationType) {
		Collection<T> anns = new ArrayList<T>(2);

		// look at raw annotation
		T ann = ae.getAnnotation(annotationType);
		if (ann != null) {
			anns.add(ann);
		}

		// scan meta-annotations
		for (Annotation metaAnn : ae.getAnnotations()) {
			ann = metaAnn.annotationType().getAnnotation(annotationType);
			if (ann != null) {
				anns.add(ann);
			}
		}

		return (anns.isEmpty() ? null : anns);
	}

	@Override
	public boolean equals(Object other) {
		return (this == other || other instanceof SpringCacheAnnotationParser);
	}

	@Override
	public int hashCode() {
		return SpringCacheAnnotationParser.class.hashCode();
	}

}

