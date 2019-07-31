package be.utils;

import be.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;


public class UserMarshallingServiceImpl {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("${exportPath}")
    String exportPath;

    public UserMarshallingServiceImpl() {
    }

    //Converts Object to XML file
    public void doMarshaling(String fileName, Object object) {


        try {
            FileWriter fileWriter = new FileWriter(exportPath + fileName);
            marshaller.marshal(object, new StreamResult(fileWriter));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object doUnMarshalling(String text) {

        try {
            unmarshaller.supports(User.class);
            return unmarshaller.unmarshal(new StreamSource(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
}
