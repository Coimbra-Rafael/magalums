package tech.buildrun.magalums.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.magalums.dtos.ScheduleNotificationDto;
import tech.buildrun.magalums.entity.Notification;
import tech.buildrun.magalums.entity.Status;
import tech.buildrun.magalums.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@Setter
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Optional<Notification> findById(Long notificationId){
        return this.notificationRepository.findById(notificationId);
    }

    @Transactional
    public void scheduleNotification(ScheduleNotificationDto scheduleNotificationDto) {
        this.notificationRepository.save(scheduleNotificationDto.toNotification());
    }

    @Transactional
    public void cancelNotification(Long notificationId) {
        var notification = this.notificationRepository.findById(notificationId);

        if(notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELLED.toStatus());
            this.notificationRepository.save(notification.get());
        }
    }

    @Transactional
    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = this.notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.Values.PENDING.toStatus(),Status.Values.ERROR.toStatus()),
                dateTime
        );

        notifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return notification -> {
            //TODO - REALIZAR O ENVIO da NOTIFICAÇÃO

            notification.setStatus(Status.Values.SUCCESS.toStatus());
            this.notificationRepository.save(notification);
        };
    }
}
