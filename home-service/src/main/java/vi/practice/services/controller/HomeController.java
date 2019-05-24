package vi.practice.services.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import protobuf.home.model.*;
import vi.practice.services.data.*;

import java.util.logging.*;


@RestController
public class HomeController {

    @Autowired
    private HomeRepository repository;

    protected Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/homes/{id}", produces = "application/x-protobuf")
    public HomeProto.Home findByNumber(@PathVariable("id") Integer id) {
        logger.info(String.format("Home.findById(%s)", id));
        return repository.findById(id);
    }

    @RequestMapping(value = "/homes/customer/{customer}", produces = "application/x-protobuf")
    public HomeProto.Homes findByCustomer(@PathVariable("customer") Integer customerId) {
        logger.info(String.format("Homes.findByCustomer(%s)", customerId));
        return HomeProto.Homes.newBuilder().addAllHome(repository.findByCustomer(customerId)).build();
    }

    @RequestMapping(value = "/homes", produces = "application/x-protobuf")
    public HomeProto.Homes findAll() {
        logger.info("Account.findAll()");
        return HomeProto.Homes.newBuilder().addAllHome(repository.findAll()).build();
    }

}
