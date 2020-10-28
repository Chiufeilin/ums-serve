package cn.kyt.ums.utils.excel;

public interface ExcelRecordValidator<T> {
    String valid(T record);
}
