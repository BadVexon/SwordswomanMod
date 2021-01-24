package TheSwordswoman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.stances.DawnflyStance;
import TheSwordswoman.stances.FreeflutterStance;


public class StancePatch {

    @SpirePatch(
            clz = AbstractStance.class,
            method = "getStanceFromName"
    )
    public static class DoStancePatch {
        @SpirePrefixPatch
        public static SpireReturn<AbstractStance> returnStance(String name) {
            if (name.equals(FreeflutterStance.STANCE_ID)) {
                return SpireReturn.Return(new FreeflutterStance());
            }
            if (name.equals(DawnflyStance.STANCE_ID)) {
                return SpireReturn.Return(new DawnflyStance());
            }
            return SpireReturn.Continue();
        }
    }
}