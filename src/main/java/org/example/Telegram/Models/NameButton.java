package org.example.Telegram.Models;

import com.vdurmont.emoji.EmojiParser;

public enum NameButton {;

    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    NameButton(String value) {
        this.value = value;
    }
}
