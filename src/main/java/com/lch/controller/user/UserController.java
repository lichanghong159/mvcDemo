package com.lch.controller.user;

import com.lch.common.GenericController;
import com.lch.entity.User;
import com.lch.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @createby: lichanghong on 2018-06-15 22:55
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController extends GenericController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * 返回jsp视图展示
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserModel",method = RequestMethod.GET)//指定get请求
    public ModelAndView getUser1(@RequestParam Integer userId){
        ModelAndView mv = new ModelAndView();//定义返回视图
        //调用userservice查询用户信息
        List<User> users = userService.queryUserById(userId);
        //将得到的用户列表内容添加到ModelAndView中
        /*这个是错误的，一定要指定key，不然页面无法取到值。类似map结构
        * mv.addObject(users);
        * */
        mv.addObject("users",users);
        //设置响应的jsp视图
        mv.setViewName("getUsers");
        logger.info("============查询用户成功=============");
        return mv;
    }
    //返回json格式数据，形式1
    @RequestMapping(value = "/getUserJson",method = RequestMethod.GET)
    @ResponseBody
    public List getUser2(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response){
        List<User> users = userService.queryUserById(userId);
        logger.info("============查询用户成功=============");
        return users;
    }
    //返回json格式数据，形式2（自定义了返回的格式）
    @RequestMapping(value = "/getUserJson2",method = RequestMethod.GET)
    public void getUsers3(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        List<User> users = userService.queryUserById(userId);
        logger.info("===============================成功查询用户列表！");
        renderSuccessString(response, users);
    }
}
