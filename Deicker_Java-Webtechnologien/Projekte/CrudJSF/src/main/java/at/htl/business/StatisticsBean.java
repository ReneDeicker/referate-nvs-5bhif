package at.htl.business;

import at.htl.model.Todo;
import at.htl.persistence.TodoRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class StatisticsBean implements Serializable {
    @Inject
    TodoRepository todoRepository;

    private BarChartModel todoBarModel;
    private List<Todo> todos;

    @PostConstruct
    public void init() {
        this.todos = todoRepository.getAllDone();
        createToDoBarChart();
    }

    private void createToDoBarChart() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        BarChartModel model = new BarChartModel();
        ChartSeries todo = new ChartSeries();
        todo.setLabel("ToDo");
        for (int i = 1; i < 15; i++){
            Date datePeriod = this.convertToDateViaInstant(LocalDate.now().minusDays(14-i));
            todo.set(format.format(datePeriod), todos.stream().filter(t -> DateUtils.isSameDay(t.getDueDate(), datePeriod)).count());
        }

        model.addSeries(todo);
        model.setLegendPosition("ne");
        model.setSeriesColors("007ad9");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Dates");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Done Todo´s");

        todoBarModel = model;
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
    public BarChartModel getTodoBarModel() {
        return todoBarModel;
    }
    public List<Todo> getTodos() {
        return todos;
    }
}
