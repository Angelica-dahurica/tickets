package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Record;
import cn.edu.nju.software.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FinanceStatisticsAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RecordService recordService;

    public RecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String execute() throws Exception {
        List<Record> recordsAdd = recordService.getAllAdd();
        List<Record> recordsMinus = recordService.getAllMinus();

        request.setAttribute("recordsAdd", recordsAdd);
        request.setAttribute("recordsMinus", recordsMinus);
        return "fianceStatistics";
    }

}
