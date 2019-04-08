package springboot.example.annotation;

import java.lang.annotation.Annotation;

/**
 * 注解参数封装类
 * @author lich
 */
public class Param {
	private String simpleName;//简单名字
	private String name;//名字
	private Class<?> type;//类型
	private Object value;//值
	private Annotation anno;//注解
	
	public Param() {
		super();
	}
	
	public Param(String simpleName,String name, Class<?> type, Object value, Annotation anno) {
		super();
		this.simpleName = simpleName;
		this.name = name;
		this.type = type;
		this.value = value;
		this.anno = anno;
	}
 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Annotation getAnno() {
		return anno;
	}
 
	public void setAnno(Annotation anno) {
		this.anno = anno;
	}
 
	public String getSimpleName() {
		return simpleName;
	}
 
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
 
	@Override
	public String toString() {
		return "Param [simpleName=" + simpleName + ", name=" + name + ", type=" + type + ", value=" + value + ", anno="
				+ anno + "]";
	}

}
