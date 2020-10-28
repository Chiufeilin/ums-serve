
package cn.kyt.ums.utils;

public class PageUtils {
	
	public static final Integer pageSize = 10;//默认每页数据量

	public static Integer getStartNum(Integer pageNo, Integer pageSize) {
		if (pageNo != null) {
			return (pageNo - 1) * pageSize;
		}
		return 0;
	}
	
	public static Integer getStartNum(Integer pageNo) {
		return getStartNum(pageNo,pageSize);
	}

	public static Integer getTotalPage(Integer count, Integer pageSize) {
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}

	public static Integer getTotalPage(Integer count) {
		return getTotalPage(count, pageSize);
	}
	
}
