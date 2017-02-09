/**
 *
 */
package org.fire.platform.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author maxwell
 * @since 1.1.5
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private static final Integer HttpStatusCodeOfNotModified = 304;
    
    private static final Integer HttpStatusCodeOfAuthFail = 401;

    private static final Integer HttpStatusCodeOfTokenTimeout = 401;

    private static final Integer HttpStatusCodeOfInvalidSession = 401;

    private static final String SchoReasonHeaderKey = "Scho-Reason";

    private static final Integer ECOfTokenTimeout = 102;
    private static final Integer ECOfInvalidSession = 101;

    private static final String XAuthTokenHeaderKey = "X-Auth-Token";
    private static final String XAuthTokenURLKey = "XAuthToken";
    private static final String XAccessTokenHeaderKey = "X-Access-Token";
    private static final String XAccessTokenURLKey = "XAccessToken";

    /**
     * Request Attribute Key Of SCHO_TOKEN
     */
    public static final String RAKOfSchoSession = "__SCHO_SESSION";

    private boolean failIfNoneOfToken = true;//如果Request Header里没有任何一个Token参数，就认为认证失败。

    private String backdoorToken = "@@SCHO_TEST_1_1@@";

   

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String xAuthToken, xAccessToken, tokenKey = null, uri;
        boolean isUriInGreyList, isUriInDummyReturnList;

        uri = request.getRequestURI();
        isUriInGreyList = isURIInGreyList(uri);
        isUriInDummyReturnList = isURIInDummyReturnList(uri);
        if (isURIInAnonymousList(uri) && isUriInGreyList == false) {
            return true;
        }

        //灰名单是1.2.1版本添加的
        if (isUriInGreyList) {
            xAccessToken = request.getHeader(XAccessTokenHeaderKey);
            if (xAccessToken == null) {
                xAccessToken = request.getParameter(XAccessTokenURLKey); //注意：这样处理，使跨域攻击变得更容易
            }
            if (xAccessToken == null) {
                return true;
            }
        }
        //End of 灰名单功能

        //兼容1.1.4及其以前版本的认证机制
        xAuthToken = request.getHeader(XAuthTokenHeaderKey);
        if (xAuthToken == null) {
            xAuthToken = request.getParameter(XAuthTokenURLKey); //注意：这样处理，使跨域攻击变得更容易
        }
        if (xAuthToken != null) {
            return true;
        }
        //end

        xAccessToken = request.getHeader(XAccessTokenHeaderKey);
        if (xAccessToken == null) {
            xAccessToken = request.getParameter(XAccessTokenURLKey); //注意：这样处理，使跨域攻击变得更容易
        }
        if (xAccessToken == null) {
            logger.info("[0x00IAI5332]Missing X-Access-Token In Header: uri={}", uri);

            if (failIfNoneOfToken) {
            	if(isUriInDummyReturnList) {
            		dummyResponseOfUnauth(request, response);
            	} else {
            		bandRequestOfUnauth(request, response);
            	}
                
                return false;
            } else {
                return true;
            }
        }
        if (xAccessToken.equals(backdoorToken)) {
            //添加后门Token控制，为了向后兼容。
            return true;
        }
        return true;

        
    }

    /**
     * 判断请求URI是否在匿名可请求的名单内
     * 匿名请求，不处理任何身份信息
     *
     * @param uri （必要）
     * @return
     */
    private boolean isURIInAnonymousList(String uri) {
        //如果URI是/**.html, /**.jsp直接返回

        if (uri == null) {
            return true;
        }

        uri = uri.replace("/front", "");
        if (uri.equals("/")) {
            return true;
        }
        if (uri.endsWith(".html") || uri.endsWith(".jsp") || uri.endsWith(".css") || uri.endsWith(".js")) {
            return true;
        }
        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".bmp")) {
            return true;
        }

        if (uri.startsWith("/login/") || uri.startsWith("/register/") || uri.startsWith("/quickAddOrganization/")
                || uri.startsWith("/svcconf/") || uri.startsWith("/newSvcconf/") || uri.startsWith("/test/")
                || uri.startsWith("/version/") || uri.startsWith("/sys/") || uri.startsWith("/monitor/")
                || uri.startsWith("/DlPageVersion/") || uri.startsWith("/helpAndSupport/") || uri.startsWith("/styles/")
                || uri.startsWith("/cgi-bin/") || uri.startsWith("/sign/") || uri.startsWith("/resPwd/") ) {
            return true;
        }

        return false;
    }

    /**
     * 判断请求URI是否在灰名单中（同时可以作为匿名请求功能）
     * 如果是灰名单请求，会尽量解释身份信息并保存到会话中。
     *
     * @param uri
     * @return
     */
    private boolean isURIInGreyList(String uri) {
        if (uri == null) {
            return false;
        }

        uri = uri.replace("/front", "");

        if (uri.startsWith("/r/")) {
            return true;
        }

        return false;
    }
    
    /**
     * 为了向后兼容旧版APP, 返回空的成功结果
     * 比如：Android某些旧版本APP，在用户推出后还会继续调用DailyCheckIn接口，但是对于后台来说这是数据是没有意义的，所以只能直接返回空的失败结果
     * @param uri
     * @return
     */
    private boolean isURIInDummyReturnList(String uri) {
    	if (uri == null) {
            return false;
        }

        uri = uri.replace("/front", "");

        if (uri.startsWith("/heartBeat/android/DailyCheckIn/V1")) {
            return true;
        }

        return false;
    }
    
    private void dummyResponseOfUnauth(HttpServletRequest request, HttpServletResponse response) {
    	response.setStatus(HttpStatusCodeOfNotModified);
        response.addHeader(SchoReasonHeaderKey, "Missing SessionID In Header.");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(0);
    }
    

    private void bandRequestOfUnauth(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatusCodeOfAuthFail);
        response.addHeader(SchoReasonHeaderKey, "Missing SessionID In Header.");
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        return;
    }

    public boolean isFailIfNoneOfToken() {
        return failIfNoneOfToken;
    }

    public void setFailIfNoneOfToken(boolean failIfNoneOfToken) {
        System.out.println("------failIfNoneOfToken = " + failIfNoneOfToken);
        this.failIfNoneOfToken = failIfNoneOfToken;
    }

    public String getBackdoorToken() {
        return backdoorToken;
    }

    public void setBackdoorToken(String backdoorToken) {
        if (backdoorToken != null && (backdoorToken.equals("@@NONE@@") || StringUtils.hasText(backdoorToken) == false)) {
            backdoorToken = null;
        }
        this.backdoorToken = backdoorToken;
    }

}
