package fe.webservice;

import be.business.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

@WebService(endpointInterface = "fe.webservice.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public FlowersStockWebServiceImpl(){}

    @Override
    @Transactional
    public void increaseFlowersStockSize(Long count) {

        flowerBusinessService.increaseFlowersStockSize(count);
    }
}
