package lt.techin.demo.dto;

import lt.techin.demo.model.RunningEvent;

import java.util.List;

public class RunningEventMapper {

  public static List<RunningEventDTO> toBookDTOList(List<RunningEvent> runningEvents) {
    List<RunningEventDTO> result = runningEvents.stream()
            .map(runningEvent -> new RunningEventDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants()))
            .toList();

    return result;
  }

  public static RunningEventDTO toRunningEventDTO(RunningEvent runningEvent) {
    return new RunningEventDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants());
  }

  public static RunningEvent toRunningEvent(RunningEventDTO runningEventDTO) {
    RunningEvent runningEvent = new RunningEvent();
    updateRunningEventFromDTO(runningEvent, runningEventDTO);


    return runningEvent;
  }

  public static void updateRunningEventFromDTO(RunningEvent runningEvent, RunningEventDTO runningEventDTO) {
    runningEvent.setName(runningEventDTO.name());
    runningEvent.setCalendarDate(runningEventDTO.calendarDate());
    runningEvent.setLocation(runningEventDTO.location());
    runningEvent.setMaxParticipants(runningEventDTO.maxParticipants());
  }
}
