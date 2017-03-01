
package scenario;
		public class LaneInfoElementValue{
			private Class<?> dataTypeClass;
			private Object value;
			public LaneInfoElementValue(Object value){
				this.dataTypeClass = value.getClass();
				this.value = value;
			}
			
			public Class<?> getDataTypeClass(){
				return dataTypeClass;
			}
			public Object getValue(){
				return value;
			}
		}

