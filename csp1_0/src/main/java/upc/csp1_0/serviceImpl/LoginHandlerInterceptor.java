package upc.csp1_0.serviceImpl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:登录拦截器
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登录，返回登录页面
            request.setAttribute("msg","没有登录权限");
            request.getRequestDispatcher("/Beforeindex.html").forward(request,response);
            return false;
        }else {
            //已登录，请求放行
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{

    }
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex)throws Exception{

    }
}

