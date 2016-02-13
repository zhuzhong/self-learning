/**
 * 
 */
package com.zz.learning.netty5.chap12;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

/**
 * @author sunff
 *
 */
public class MarshallingCodecFactory {

    /**
     * @return
     */
    public static Marshaller buildMarshalling() throws IOException{
        MarshallerFactory mf = new SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return mf.createMarshaller(configuration);
        // return null;
    }

    /**
     * @return
     */
    public static Unmarshaller buildUnMarshalling() throws IOException{

        MarshallerFactory mf = new SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return mf.createUnmarshaller(configuration);
    }

}
