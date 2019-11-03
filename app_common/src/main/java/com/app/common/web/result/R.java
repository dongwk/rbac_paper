/**
 * @author Administrator
 *
 */
package com.app.common.web.result;


import com.app.common.web.result.enums.R_CODE;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 统一 web 返回值定义，定义 http 状态码和 responseBody 中的 code 码
 * ResponseStatus: http 状态码
 * ResponseBody: http 返回内容
 * ResponseBody 中 R.Model 为内容标准
 * R.Model 中 code 为 http 状态码或自定义，code 统一成功为 200
 *
 * code 不同方法下的值:
 * SUCCESS/ERROR/MODEL(HttpStatus) code 为标准
 * MODEL(String) code 为自定义，检测如果是标准则返回标准，否则返回自定义 ResponseStatus 为 500
 *
 */
public class R<T> extends ResponseEntity<R.Model> {

	private static final Model MODEL = new Model(null, null, null);

	private R() {
	    super(MODEL, HttpStatus.OK);
	}

	private R(HttpStatus httpStatus) {
        super(httpStatus);
		Model m = new Model();
        m.code = httpStatus.value()+"";
        m.msg = httpStatus.getReasonPhrase();
    }

	private R(Model m, HttpStatus httpStatus) {
		super(m, httpStatus);
		m = new Model();
		m.code = httpStatus.value()+"";
		m.msg = httpStatus.getReasonPhrase();
	}

	public static R SUCCESS(){
		HttpStatus httpStatus = HttpStatus.OK;
		return new R(new Model(httpStatus.value()+"", httpStatus.getReasonPhrase(), null), httpStatus);
	}

	public static <T> R<T> SUCCESS(T t){
		HttpStatus httpStatus = HttpStatus.OK;
		return new R(new Model(httpStatus.value()+"", httpStatus.getReasonPhrase(), t), httpStatus);
	}

	public static R SUCCESS(String msg){
		HttpStatus httpStatus = HttpStatus.OK;
		return new R(new Model(httpStatus.value()+"", msg, null), httpStatus);
	}

	public static R ERROR(){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return new R(new Model(httpStatus.value()+"", R_CODE.ERROR.msg, null), httpStatus);
	}

	public static <T> R<T> ERROR(T t){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return new R(new Model(httpStatus.value()+"", R_CODE.ERROR.msg, t), httpStatus);
	}

	public static R ERROR(String msg){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return new R(new Model(httpStatus.value()+"", msg, null), httpStatus);
	}

	public static R ERROR(HttpStatus httpStatus, String msg){
		return new R(new Model(httpStatus.value()+"", msg, null), httpStatus);
	}

	public static R MODEL(HttpStatus httpStatus){
		return new R(new Model(httpStatus != null ? httpStatus.value()+"" : null, httpStatus != null ? httpStatus.getReasonPhrase() : null, null), httpStatus);
	}

	public static R MODEL(HttpStatus httpStatus, String msg){
		return new R(new Model(httpStatus != null ? httpStatus.value()+"" : null, msg, null), httpStatus);
	}

	public static <T> R<T> MODEL(HttpStatus httpStatus, T t){
		return new R(new Model(httpStatus != null ? httpStatus.value()+"" : null, httpStatus != null ? httpStatus.getReasonPhrase() : null, t), httpStatus);
	}


	/**
	 * 非标准 httpStatus，返回 500
	 *
	 * @param httpStatus
	 * @return
	 */
	public static R MODEL(int httpStatus){
		HttpStatus httpStatus1 = HttpStatus.valueOf(httpStatus);
		return httpStatus1 != null ? new R(new Model(httpStatus1 != null ? httpStatus1.value()+"" : null, httpStatus1 != null ? httpStatus1.getReasonPhrase() : null, null), httpStatus1)
									: new R(new Model(httpStatus+"", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 非标准 httpStatus，返回 500
	 *
	 * @param httpStatus
	 * @param msg
	 * @return
	 */
	public static R MODEL(int httpStatus, String msg){
		HttpStatus httpStatus1 = HttpStatus.valueOf(httpStatus);
		return httpStatus1 != null ? new R(new Model(httpStatus1 != null ? httpStatus1.value()+"" : null, msg, null), httpStatus1)
									: new R(new Model(httpStatus+"", msg, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 非标准 httpStatus，返回 500
	 *
	 * @param httpStatus
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> R<T> MODEL(String httpStatus, T t){
		HttpStatus httpStatus1 = HttpStatus.valueOf(httpStatus);
		return httpStatus1 != null ? new R(new Model(httpStatus1 != null ? httpStatus1.value()+"" : null, httpStatus1 != null ? httpStatus1.getReasonPhrase() : null, t), httpStatus1)
									: new R(new Model(httpStatus,  null, t), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Data
	static class Model<T>{
		private String code;
		private String msg;
		private T data;

		public Model() {}
		public Model(String code, String msg, T data) {
			this.code = code;
			this.msg = msg;
			this.data = data;
		}

		@Override
		public String toString() {
			return "R [code=" + code + ", msg=" + msg + ", data=" + data + "]";
		}

	}


}