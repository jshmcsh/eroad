package jlxy.eroad.server.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import jlxy.eroad.server.bean.param.ComputeParam;
import jlxy.eroad.server.bean.param.LoginBean;
import jlxy.eroad.server.bean.result.ComputeResult;
import jlxy.eroad.server.core.CompanyDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yecq.baseframework.plain.service.Sret;

/**
 *
 * @author cp
 */
@Service
@Transactional
public class CompanyService {
    @Autowired
    private CompanyDatabase cdb;
     public Sret login(LoginBean param) {
         //取用户名、密码，调用访问数据库函数，并接受返回的list
        Sret sr = new Sret();
        String username = param.getUsername();
        String password = param.getPassword();
        List loginRet=cdb.getUserList(username);
        if(loginRet.isEmpty()){
            sr.setFail("用户不存在");
        }
        //迭代，比对信息
        
        Iterator <Map<String,Object>> it=loginRet.iterator();
        for(;it.hasNext();){
            Map<String,Object> compareMap=it.next();
            if(compareMap.get("passwd")!=null){
                
                if(compareMap.get("passwd").equals(password)){                                
                   // sr.setOk("登录成功");
                    System.out.println("登录成功");
                    
                }
                else{
                    sr.setFail("密码错误");
                    
                }
            }
        }
        return sr;
    }
     public Sret regist(LoginBean param) {
         //注册，返回用户uid
        String username = param.getUsername();
        String password = param.getPassword();
        String registRet=cdb.addUser(username, password);
        Sret sr = new Sret();
        sr.setData(registRet);
        return sr;
    }
      public Sret logout(ComputeParam param) {
        int a = param.getA();
        int b = param.getB();
        int c = a + b;
        ComputeResult ret = new ComputeResult(c);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }

       public Sret ShowCarAround(ComputeParam param) {
        int a = param.getA();
        int b = param.getB();
        int c = a + b;
        ComputeResult ret = new ComputeResult(c);
        Sret sr = new Sret();
        sr.setOk();
        sr.setData(ret);
        return sr;
    }


    public Sret throwException() {
        throw new IllegalStateException("错误的状态");
    }
}
