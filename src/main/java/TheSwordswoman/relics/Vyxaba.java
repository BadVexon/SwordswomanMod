package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import TheSwordswoman.TheSwordswoman;
import TheSwordswoman.actions.RandomMatchToHandAction;

public class Vyxaba extends AbstractBlazRelic {
    public static final String ID = makeID("Vyxaba");

    public Vyxaba() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    private boolean cardSelected = true;

    public void onEquip() {
        this.cardSelected = false;// 24
        if (AbstractDungeon.isScreenUp) {// 25
            AbstractDungeon.dynamicBanner.hide();// 26
            AbstractDungeon.overlayMenu.cancelButton.hide();// 27
            AbstractDungeon.previousScreen = AbstractDungeon.screen;// 28
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;// 30
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, "Choose a card to duplicate.", false, false, false, false);// 32
    }// 40

    public void update() {
        super.update();// 44
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {// 45 46
            this.cardSelected = true;// 47
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();// 49
            c.inBottleFlame = false;// 50
            c.inBottleLightning = false;// 51
            c.inBottleTornado = false;// 52
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));// 53
            AbstractDungeon.gridSelectScreen.selectedCards.clear();// 55
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;// 57
            AbstractDungeon.gridSelectScreen.selectedCards.clear();// 58
        }

    }// 61

    @Override
    public void atTurnStart() {
        grayscale = false;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (!grayscale) {
            flash();
            grayscale = true;
            atb(new RandomMatchToHandAction(1, targetCard));
        }
    }
}
