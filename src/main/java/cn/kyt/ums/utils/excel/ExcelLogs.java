package cn.kyt.ums.utils.excel;

import java.util.List;

/**
 * The <code>ExcelLogs</code>
 *
 * @author sargeras.wang
 * @version 1.0, Created at 2013年9月17日
 */
public class ExcelLogs {
    private int totalRowCount;
    private int errorRowCount;
    private String errorLogFilePath;

    private Boolean hasError;
    private List<ExcelLog> logList;
    /**
     *
     */
    public ExcelLogs() {
        super();
        hasError = false;
    }


    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public int getErrorRowCount() {
        return errorRowCount;
    }

    public void setErrorRowCount(int errorRowCount) {
        this.errorRowCount = errorRowCount;
    }

    /**
     * @return the hasError
     */
    public Boolean getHasError() {
        return hasError;
    }

    /**
     * @param hasError
     *            the hasError to set
     */
    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * @return the logList
     */
    public List<ExcelLog> getLogList() {
        return logList;
    }

//    public List<ExcelLog> getErrorLogList() {
//        List<ExcelLog> errList = new ArrayList<>();
//        for (ExcelLog log : this.logList) {
//            if (log != null && StringUtils.isNotBlank(log.getLog())) {
//                errorLogList.add(log);
//            }
//        }
//        return errorLogList;
//    }

    /**
     * @param logList
     *            the logList to set
     */
    public void setLogList(List<ExcelLog> logList) {
        this.logList = logList;
    }

    public String getErrorLogFilePath() {
        return errorLogFilePath;
    }

    public void setErrorLogFilePath(String errorLogFilePath) {
        this.errorLogFilePath = errorLogFilePath;
    }
}