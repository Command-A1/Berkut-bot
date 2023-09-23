package org.example.ClientDataBase.ViewClient;

import lombok.SneakyThrows;
import org.example.Main;
import org.example.Telegram.Models.Emoji;
import org.glassfish.jersey.server.Uri;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.net.URL;

public class ClientMessageOrder {
    @SneakyThrows
    public static void sendMessageOrder(String idClient, String orderId, String waiterName) {
        SendMessage s = new SendMessage();
        s.setText(Emoji.WHITE_CHECK_MARK.get() + "Ваш заказ под номером -" + orderId + ".\nПринят сейчас к вам подойдет официант " + waiterName);
        s.setChatId(idClient);
        Main.e.executeMessage(s);
    }

    @SneakyThrows
    public static void sendPhotoWaiterWithMessageOrderId(String idClient, String orderId, String waiterName, String imgUrl) {
        URL url = new URL(imgUrl);
        InputFile photo = new InputFile(String.valueOf(url));
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(photo);
        sendPhoto.setChatId(idClient);
        sendPhoto.setCaption(Emoji.WHITE_CHECK_MARK.get() + "Заказ " + orderId + " принят.\n Сейчас к вам подойдет официант " + waiterName + Emoji.BLUSH.get());
        Main.e.execute(sendPhoto);
    }

}

