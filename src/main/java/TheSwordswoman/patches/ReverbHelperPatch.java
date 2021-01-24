package TheSwordswoman.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import TheSwordswoman.cards.AbstractBlazCard;

public class ReverbHelperPatch {

    @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
    public static class SecondEnergyRenderPatch {
        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (__instance.cost > -2 && !__instance.isLocked && __instance.isSeen && AbstractDungeon.player != null && AbstractDungeon.player.hand != null && AbstractDungeon.player.hand.contains(__instance) && __instance instanceof AbstractBlazCard && __instance.rawDescription.contains("Reverb")) {// 2706
                String text = Integer.toString(((AbstractBlazCard) __instance).reverb() + 1);

                FontHelper.renderRotatedText(sb, FontHelper.cardEnergyFont_L, text, __instance.current_x, __instance.current_y,
                        -135.0F * __instance.drawScale * Settings.scale,
                        85.0F * __instance.drawScale * Settings.scale,
                        __instance.angle, false, Color.WHITE.cpy());
            }
        }
    }
}
