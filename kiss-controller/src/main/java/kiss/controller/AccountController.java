package kiss.controller;


import kiss.feign.AccountServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceFeign accountServiceFeign;

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String get() {
        String name = accountServiceFeign.get();
        System.out.println(name);
        return name;
    }
}
