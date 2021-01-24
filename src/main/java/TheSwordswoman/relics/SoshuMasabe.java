package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.BlazMod;
import TheSwordswoman.TheSwordswoman;

public class SoshuMasabe extends AbstractBlazRelic {
    public static final String ID = makeID("SoshuMasabe");

    public SoshuMasabe() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        atb(new DrawCardAction(2));
        atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 2, false));
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        atTurnStartPostDraw();
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Venustas.ID)) {// 52
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {// 53
                if (AbstractDungeon.player.relics.get(i).relicId.equals(Venustas.ID)) {// 54
                    this.instantObtain(AbstractDungeon.player, i, true);// 55
                    break;// 56
                }
            }
        } else {
            super.obtain();// 60
        }
    }// 62

    @Override
    public boolean canSpawn() {
        return (AbstractDungeon.player.hasRelic(Venustas.ID));
    }

    @Override
    public String getUpdatedDescription() {
        String name = (new Venustas()).name;// 38
        StringBuilder sb = new StringBuilder();// 39
        String[] var3 = name.split(" ");

        for (String word : var3) {// 40
            sb.append("[#").append(BlazMod.todoColor.toString()).append("]").append(word).append("[] ");// 41
        }

        sb.setLength(sb.length() - 1);// 43
        sb.append("[#").append(BlazMod.todoColor.toString()).append("]");// 44
        return this.DESCRIPTIONS[0] + sb.toString() + this.DESCRIPTIONS[1];// 46
    }
}
