package jorgeandcompany.loveletter;

import java.util.ArrayList;

/**
 * Created by Penguins94 on 11/13/2015.
 */
public class SkinRes {
    public static ArrayList <String> skinNames;
    public static String activeSkin;

    static {
        skinNames = new ArrayList<>();
    }

    /**
     * Gets a card graphic as an drawable id based on the current skin theme that is set and the keys that are passed.
     * @param card the card number
     * @param orientation the orientation of the card
     * @return the drawable id based on the current skin theme and the keys passed.
     */
    public static int skinRes (int card, String orientation) {
        if (activeSkin.equalsIgnoreCase("Magi Skin")) {
            return skinResMagi(card,orientation);
        }
        else if (activeSkin.equalsIgnoreCase("Avengers Skin")){
            return skinResAvengers(card,orientation);
        }
        else {
            return skinResEmblem(card, orientation);
        }
    }

    /**
     * Helper method for returning the corresponding drawable id based off the current skin theme that is set.
     * @param card the card number
     * @param orientation the orientation of the card
     * @return the drawable id based on the current skin theme and the keys passed.
     */
    private static int skinResMagi(int card, String orientation) {
        if (orientation.equalsIgnoreCase("up")) {
            switch (card) {
                case 1:
                    return R.drawable.aladdinver;
                case 2:
                    return R.drawable.alibabaver;
                case 3:
                    return R.drawable.scheherazadever;
                case 4:
                    return R.drawable.morgianacardver;
                case 5:
                    return R.drawable.hakuryuuver;
                case 6:
                    return R.drawable.sinbadver;
                case 7:
                    return R.drawable.yunanver;
                case 8:
                    return R.drawable.judarver;
                default:
                    return R.drawable.magi_up;
            }
        } else if (orientation.equalsIgnoreCase("left")) {
            switch (card) {
                case 1:
                    return R.drawable.aladdinleft;
                case 2:
                    return R.drawable.alibabaleft;
                case 3:
                    return R.drawable.scheherazadeleft;
                case 4:
                    return R.drawable.morgianacardleft;
                case 5:
                    return R.drawable.hakuryuuleft;
                case 6:
                    return R.drawable.sinbadleft;
                case 7:
                    return R.drawable.yunanleft;
                case 8:
                    return R.drawable.judarleft;
                default:
                    return R.drawable.magi_left;
            }
        } else if (orientation.equalsIgnoreCase("right")) {
            switch (card) {
                case 1:
                    return R.drawable.aladdinright;
                case 2:
                    return R.drawable.alibabaright;
                case 3:
                    return R.drawable.scheherazaderight;
                case 4:
                    return R.drawable.morgianacardright;
                case 5:
                    return R.drawable.hakuryuuright;
                case 6:
                    return R.drawable.sinbadright;
                case 7:
                    return R.drawable.yunanright;
                case 8:
                    return R.drawable.judarright;
                default:
                    return R.drawable.magi_right;
            }
        } else {
            switch (card) {
                case 1:
                    return R.drawable.aladdindown;
                case 2:
                    return R.drawable.alibabadown;
                case 3:
                    return R.drawable.scheherazadedown;
                case 4:
                    return R.drawable.morgianacarddown;
                case 5:
                    return R.drawable.hakuryuurdown;
                case 6:
                    return R.drawable.sinbaddown;
                case 7:
                    return R.drawable.yunandown;
                case 8:
                    return R.drawable.judardown;
                default:
                    return R.drawable.magi_down;
            }
        }
    }

    /**
     * Helper method for returning the corresponding drawable id based off the current skin theme that is set.
     * @param card the card number
     * @param orientation the orientation of the card
     * @return the drawable id based on the current skin theme and the keys passed.
     */
    private static int skinResAvengers (int card, String orientation) {
        if (orientation.equalsIgnoreCase("up")) {
            switch (card) {
                case 1:
                    return R.drawable.hawkeye;
                case 2:
                    return R.drawable.black_widow;
                case 3:
                    return R.drawable.thor;
                case 4:
                    return R.drawable.captian_america;
                case 5:
                    return R.drawable.hulk;
                case 6:
                    return R.drawable.loki;
                case 7:
                    return R.drawable.ironman;
                case 8:
                    return R.drawable.ultron;
                default:
                    return R.drawable.avengers_back;
            }
        } else if (orientation.equalsIgnoreCase("left")) {
            switch (card) {
                case 1:
                    return R.drawable.hawkeye_left;
                case 2:
                    return R.drawable.black_widow_left;
                case 3:
                    return R.drawable.thor_left;
                case 4:
                    return R.drawable.captian_america_left;
                case 5:
                    return R.drawable.hulk_left;
                case 6:
                    return R.drawable.loki_left;
                case 7:
                    return R.drawable.ironman_left;
                case 8:
                    return R.drawable.ultron_left;
                default:
                    return R.drawable.avengers_back_left;
            }
        } else if (orientation.equalsIgnoreCase("right")) {
            switch (card) {
                case 1:
                    return R.drawable.hawkeye_right;
                case 2:
                    return R.drawable.black_widow_right;
                case 3:
                    return R.drawable.thor_right;
                case 4:
                    return R.drawable.captian_america_right;
                case 5:
                    return R.drawable.hulk_right;
                case 6:
                    return R.drawable.loki_right;
                case 7:
                    return R.drawable.ironman_right;
                case 8:
                    return R.drawable.ultron_right;
                default:
                    return R.drawable.avengers_back_right;
            }
        } else {
            switch (card) {
                case 1:
                    return R.drawable.hawkeye_down;
                case 2:
                    return R.drawable.black_widow_down;
                case 3:
                    return R.drawable.thor_down;
                case 4:
                    return R.drawable.captian_america_down;
                case 5:
                    return R.drawable.hulk_down;
                case 6:
                    return R.drawable.loki_down;
                case 7:
                    return R.drawable.ironman_down;
                case 8:
                    return R.drawable.ultron_down;
                default:
                    return R.drawable.avengers_back_down;
            }
        }
    }

    /**
     * Helper method for returning the corresponding drawable id based off the current skin theme that is set.
     * @param card the card number
     * @param orientation the orientation of the card
     * @return the drawable id based on the current skin theme and the keys passed.
     */
    private static int skinResEmblem (int card, String orientation) {
        if (orientation.equalsIgnoreCase("up")) {
            switch (card) {
                case 1:
                    return R.drawable.lucina;
                case 2:
                    return R.drawable.lissa;
                case 3:
                    return R.drawable.chrom;
                case 4:
                    return R.drawable.fredrick;
                case 5:
                    return R.drawable.aversa;
                case 6:
                    return R.drawable.grangel;
                case 7:
                    return R.drawable.validar;
                case 8:
                    return R.drawable.emmeryn;
                default:
                    return R.drawable.fire_emblem_back;
            }
        } else if (orientation.equalsIgnoreCase("left")) {
            switch (card) {
                case 1:
                    return R.drawable.lucina_left;
                case 2:
                    return R.drawable.lissa_left;
                case 3:
                    return R.drawable.chrom_left;
                case 4:
                    return R.drawable.fredrick_left;
                case 5:
                    return R.drawable.aversa_left;
                case 6:
                    return R.drawable.grangel_left;
                case 7:
                    return R.drawable.validar_left;
                case 8:
                    return R.drawable.emmeryn_left;
                default:
                    return R.drawable.fire_emblem_back_left;
            }
        } else if (orientation.equalsIgnoreCase("right")) {
            switch (card) {
                case 1:
                    return R.drawable.lucina_right;
                case 2:
                    return R.drawable.lissa_right;
                case 3:
                    return R.drawable.chrom_right;
                case 4:
                    return R.drawable.fredrick_right;
                case 5:
                    return R.drawable.aversa_right;
                case 6:
                    return R.drawable.grangel_right;
                case 7:
                    return R.drawable.validar_right;
                case 8:
                    return R.drawable.emmeryn_right;
                default:
                    return R.drawable.fire_emblem_back_right;
            }
        } else {
            switch (card) {
                case 1:
                    return R.drawable.lucina_down;
                case 2:
                    return R.drawable.lissa_down;
                case 3:
                    return R.drawable.chrom_down;
                case 4:
                    return R.drawable.fredrick_down;
                case 5:
                    return R.drawable.aversa_down;
                case 6:
                    return R.drawable.grangel_down;
                case 7:
                    return R.drawable.validar_down;
                case 8:
                    return R.drawable.emmeryn_down;
                default:
                    return R.drawable.fire_emblem_back_down;
            }
        }
    }

    /**
     * Gets the button graphic as an drawable id based on the current skin theme that is set.
     * @return the drawable id based on the current skin theme.
     */
    public static int getButtonTheme() {
        if (activeSkin.equalsIgnoreCase("Magi Skin")) {
            return R.drawable.button_back_magi;
        }
        else if (activeSkin.equalsIgnoreCase("Avengers Skin")){
            return R.drawable.button_back_avenger;
        }
        else {
            return R.drawable.button_back_fe;
        }
    }

    /**
     * Gets the playing table graphic as an drawable id based on the current skin theme that is set.
     * @return the drawable id based on the current skin theme.
     */
    public static int getTableTheme() {
        if (activeSkin.equalsIgnoreCase("Magi Skin")) {
            return R.drawable.table_top_magi;
        }
        else if (activeSkin.equalsIgnoreCase("Avengers Skin")){
            return R.drawable.table_top_avenger;
        }
        else {
            return R.drawable.table_top_fe;
        }
    }
}
