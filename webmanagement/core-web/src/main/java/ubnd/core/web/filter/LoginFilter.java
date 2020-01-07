package ubnd.core.web.filter;

import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.SessionUtils;
import ubnd.core.dto.ExtendPermissionUserDto;
import ubnd.core.dto.UserDto;
import ubnd.core.utlis.WebUriUtils;
import ubnd.core.web.common.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String url = WebUriUtils.getCurrentUrlWithoutParams(request);
        String loginURI = url + "/login.html";
        String homeURI = url + "/home.html";
        String permissionDeniedURI = url + "/permission-denied.html";
        UserDto dto = (UserDto) session.getAttribute(WebConstants.LOGIN);
        boolean loggedIn = dto != null;
        boolean loginRequest = request.getRequestURL().toString().equals(loginURI);
        String urlString = request.getRequestURL().toString();
        if (urlString.contains(".css") || urlString.contains(".js")
                || urlString.contains(".jpg") || urlString.contains(".png") || urlString.contains(".mp3")
                || urlString.contains(".json") || urlString.contains("/ajax") || urlString.contains("/logout.html")
                || urlString.contains("/permission-denied.html") || urlString.contains("/login.html") || urlString.contains("/error.html")
                || urlString.contains(".eot") || urlString.contains(".ttf") || urlString.contains(".otf")
                || urlString.contains(".xml") || urlString.contains(".txt")
                || urlString.contains(".svg") || urlString.contains(".woff") || urlString.contains("/api/")) {
            filterChain.doFilter(request, response);
        } else {
            if (loggedIn) {
                if (loginRequest) {
                    //loggedIn and url = login
                    response.sendRedirect(homeURI);
                } else {
//                    filterChain.doFilter(request, response);
                    //loggedIn and url bat ky
                    if(checkDoFilter(request, urlString)){
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendRedirect(permissionDeniedURI);
                    }
                }
            } else if (!loginRequest) {
                response.sendRedirect(loginURI);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    public void destroy() {

    }

    /**
     * check allowed url
     * @param urlString
     * @return true is allow
     */
    private boolean checkDoFilter(HttpServletRequest request, String urlString) {
        boolean check = false;
        List<String> urlAllowedList = (List<String>) SessionUtils.getInstance().getValue(request, CoreConstant.URL_ALLOWED_LIST);
        urlAllowedList.add("/home.html");
        urlAllowedList.add("/info.html");
        urlAllowedList.add("/qna.html");
        for (String url : urlAllowedList) {
            if (urlString.contains(url)) {
                return true;
            }
        }
        return check;
    }
}
