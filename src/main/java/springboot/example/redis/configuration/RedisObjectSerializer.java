package springboot.example.redis.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 此时定义的序列化操作表示可以序列化所有类的对象，当然，这个对象所在的类一定要实现序列化接口
 * Created by Lich on 2019年9月17日.
 *
 */
public class RedisObjectSerializer implements RedisSerializer<Object>{
	
	//为了方便进行对象与字节数组的转换，所以应该首先准备两个转换器
	private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
	private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0]; //做一个空数组，不是null

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if(t == null){				//此时没有要序列化的对象出现，所以返回字节数组应该是一个空数组
			return EMPTY_BYTE_ARRAY;
		}
		return this.serializingConverter.convert(t);
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null || bytes.length == 0){			//此时没有对象的内容信息
			return null;
		}
		return this.deserializingConverter.convert(bytes);
	}
	
	

}
