package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

public class Quickdraw extends AbstractBlazCard {

    public final static String ID = makeID("Quickdraw");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 24;
    private static final int UPG_BLOCK = 6;

    public Quickdraw() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (q.type == CardType.ATTACK) {
                cantUseMessage = "I've played an Attack this turn.";
                return false;
            }
        }
        return super.canUse(p, m);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, 1));
        atb(new PressEndTurnButtonAction());
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}