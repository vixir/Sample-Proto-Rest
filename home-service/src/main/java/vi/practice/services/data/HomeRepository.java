package vi.practice.services.data;

import java.util.*;
import java.util.stream.*;

import static protobuf.home.model.HomeProto.*;

public class HomeRepository {

    List<Home> homes;

    public HomeRepository(List<Home> homes) {
        this.homes = homes;
    }

    public List<Home> findAll() {
        return homes;
    }

    public List<Home> findByCustomer(int customerId) {
        return homes.stream().filter(it -> it.getOwnerId() == customerId).collect(Collectors.toList());
    }

    public Home findById(Integer id) {
        return homes.stream().filter(it -> it.getId() == id).findFirst().get();
    }

}
