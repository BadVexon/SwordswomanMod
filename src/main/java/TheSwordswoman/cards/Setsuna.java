package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Setsuna extends AbstractBlazCard {

    public final static String ID = makeID("Setsuna");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    public Setsuna() {
        super(ID, 5, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < reverb(); i++) {
            atb(new DiscardAction(p, p, p.hand.size(), true));
            this.addToBot(new SkipEnemiesTurnAction());// 36
            this.addToBot(new PressEndTurnButtonAction());// 37
        }
    }

    public void upp() {
        upgradeBaseCost(4);
    }
}