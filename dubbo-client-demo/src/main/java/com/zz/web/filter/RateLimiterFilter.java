/**
 * 
 */
package com.zz.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author Administrator
 *
 */
public class RateLimiterFilter implements Filter {

    private volatile boolean useLimit; // 是否使用限流阀
    private double permitsPerSecond;// 限流个数
    private RateLimiter rateLimiter;

    public void init() {
        if (useLimit && (permitsPerSecond == 0D)) {
            permitsPerSecond = 100000D;
        }
        if (useLimit) {
            rateLimiter = RateLimiter.create(permitsPerSecond);
        }
    }

    private static Log log = LogFactory.getLog(RateLimiterFilter.class);

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain chain) throws IOException,
            ServletException {
        if (useLimit) {
            log.info("now using ratelimitfilter....");
            rateLimiter.acquire();
            chain.doFilter(request, reponse);
        } else {
            log.info("not using ratelimitfilter...");
            chain.doFilter(request, reponse);
        }

    }

    /**
     * @param useLimit
     *            the useLimit to set
     */
    public void setUseLimit(boolean useLimit) {
        this.useLimit = useLimit;
    }

    /**
     * @param permitsPerSecond
     *            the permitsPerSecond to set
     */
    public void setPermitsPerSecond(double permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public void init(FilterConfig arg0) throws ServletException {

        init();

    }

}
