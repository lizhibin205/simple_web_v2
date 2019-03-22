package com.bytrees.cache.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ObjectRedisSerializer implements RedisSerializer<Object> {
	private static final byte[] EMPTY_BYTE = new byte[0];
    private Converter<Object, byte[]> serializer;
    private Converter<byte[], Object> deserializer;
    public ObjectRedisSerializer(Converter<Object, byte[]> serializer, Converter<byte[], Object> deserializer) {
    	this.serializer = serializer;
    	this.deserializer = deserializer;
    }

	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		// TODO Auto-generated method stub
		if (obj == null) {
			return EMPTY_BYTE;
		}
		byte[] byteArr = serializer.convert(obj);
		return byteArr;
	}

	@Override
	public Object deserialize(byte[] byteArr) throws SerializationException {
		// TODO Auto-generated method stub
		if (byteArr == null || byteArr.length == 0) {
			return null;
		}
		return deserializer.convert(byteArr);
	}

}
