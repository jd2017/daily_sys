package com.yun.daily.secure.Service;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.secure.dao.UserDao;
import com.yun.daily.secure.domain.UserModel;
import com.yun.daily.secure.factory.SecureUserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecureUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserDao userDao;

    private static Logger log = LoggerFactory.getLogger(SecureUserDetailsServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        log.info("SecureUserDetailsServiceImpl.loadUserByUsername--personUserDao.selectPersonUserByAccount(),Parameter:account='s%'",account);
        UserModel userModel = userDao.selectPersonUserByAccount(account);
        log.info("SecureUserDetailsServiceImpl.loadUserByUsername--personUserDao.selectPersonUserByAccount()--result:"+userModel);
        if(userModel!=null) {
             return SecureUserFactory.create(userModel);
        }else{
             log.error("no personuser found with account '%s'",account);
             throw new UsernameNotFoundException(String.format("no personuser found with account '%s'",account));
        }
    }

}
