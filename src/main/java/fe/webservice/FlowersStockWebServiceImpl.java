package fe.webservice;

import be.business.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.webservice.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public FlowersStockWebServiceImpl(){}

    @Override
    public void increaseFlowersStockSize(Long count) {
        flowerBusinessService.increaseFlowersStockSize(count);
    }
}
