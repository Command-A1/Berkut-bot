package org.example.Telegram.Models;

import com.vdurmont.emoji.EmojiParser;

public enum Emoji {
    ROCKET(":rocket:"),
    CROWN(":crown:"),
    AUTHOR(":man_technologist:"),
    TELEPHONE(":telephone:"),
    MENU(":clipboard:"),
    FIRE(":fire:"),
    SALAD(":green_salad:"),
    HEART(":heart:"),
    WINE(":wine_glass:"),
    ARROW_RIGHT(":arrow_right:"),
    ARROW_LEFT(":arrow_left:"),
    DISH(":fork_and_knife:"),
    WHITE_CHECK_MARK(":white_check_mark:"),
    X(":x:"),
    SMILE_BLUSH(":blush:"),

    WELCOME(":wave:"),
    CROSS(":x:"),
    BACK(":leftwards_arrow_with_hook:"),
    ORDER_LIST(":memo:"),
    BALLOON(":balloon:"),
    TADA(":tada:"),
    PAST_WEEK(":rewind:"),
    THIS_WEEK(":beach_with_umbrella:"),
    NEXT_WEEK(":fast_forward:"),
    PAINTBRUSH(":lower_left_paintbrush:"),
    RULER(":triangular_ruler:"),
    UNDERAGE(":underage:")
    ;

    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Emoji(String value) {
        this.value = value;
    }
}
