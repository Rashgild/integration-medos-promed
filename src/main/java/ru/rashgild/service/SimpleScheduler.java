package ru.rashgild.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rashgild.controller.ScheduleController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class SimpleScheduler {

    private final ScheduleController controller;

    /**
     * Запускаем синхронизация поликлиники на след. день.
     * Если запускаем в пятницу - синхронизируем еще и на воскресенье и понедельник
     * Если запускаем в субботу - синхронизируем еще и на понедельник
     */
    @Scheduled(cron = "${cron.schedule}")
    public void syncPolyclinic() {
        log.info("start SyncPolyclinic: ");
        LocalDate date = LocalDate.now().plusDays(1L); //синхронизируем на завтра
        sync(date);
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            sync(date.plusDays(1L)); //воскесенье
            sync(date.plusDays(2L)); //понедельник
        } else if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            sync(date.plusDays(1L)); //понедельник
        }
    }

    private void sync(LocalDate date) {
        log.info("Синхронизируем расписание на дату: {}", date);
        controller.syncByDate(String.valueOf(date));
    }
}
