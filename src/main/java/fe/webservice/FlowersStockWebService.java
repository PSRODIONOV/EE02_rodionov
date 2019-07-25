package fe.webservice;


import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FlowersStockWebService {
    @WebResult
    void increaseFlowersStockSize(@WebParam(name = "request") Long count);
}
