/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zz.learning.netty5.chap10.xml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Administrator
 * @date 2014年3月1日
 * @version 1.0
 */
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {
    /*
     * IBindingFactory factory = null; StringWriter writer = null;
     */
    final static String CHARSET_NAME = "UTF-8";
    final static Charset UTF_8 = Charset.forName(CHARSET_NAME);
    XStream stream = new XStream(new DomDriver("utf-8"));

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object order) throws Exception {
        /*
         * factory = BindingDirectory.getFactory(body.getClass()); writer = new
         * StringWriter(); IMarshallingContext mctx =
         * factory.createMarshallingContext(); mctx.setIndent(2);
         * mctx.marshalDocument(body, CHARSET_NAME, null, writer); String xmlStr
         * = writer.toString(); writer.close(); writer = null; ByteBuf encodeBuf
         * = Unpooled.copiedBuffer(xmlStr, UTF_8); return encodeBuf;
         */

/*        String xmlStr = stream.toXML(order);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(xmlStr, UTF_8);
        return encodeBuf;*/
        
        String s=JSON.toJSONString(order);
        System.out.println(s);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(s, UTF_8);
        return encodeBuf;
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to
     * forward to the next {@link ChannelHandler} in the {@link ChannelPipeline}
     * .
     * 
     * Sub-classes may override this method to change behavior.
     */
    @Skip
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 释放资源
        /*
         * if (writer != null) { writer.close(); writer = null; }
         */
    }

}
