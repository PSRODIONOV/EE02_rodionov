package be.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class UserMarshallingServiceImpl {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Value("${exportPath}")
    String exportPath;
    //Converts Object to XML file
    public void doMarshaling(String fileName, Object object) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(exportPath + fileName);
            marshaller.marshal(object, new StreamResult(fos));
            fos.close();
            UserSender.sendUserXml(exportPath + fileName);//Send xml of user file in out_queue
        }
        catch (Exception e) {
           e.printStackTrace();
        }
    }

}
