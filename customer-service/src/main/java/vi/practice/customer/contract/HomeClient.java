package vi.practice.customer.contract;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vi.practice.customer.contract.services.protobuf.customer.model.*;

@FeignClient(value = "home-service")
public interface HomeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/homes/customer/{customerId}")
    CustomerProto.Homes getHomes(@PathVariable("customerId") Integer customerId);

}