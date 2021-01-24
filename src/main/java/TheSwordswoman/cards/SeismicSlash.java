package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import TheSwordswoman.BlazMod;

public class SeismicSlash extends AbstractBlazCard {

    public final static String ID = makeID("SeismicSlash");

    //stupid intellij stuff ATTACK, ALL_ENEMY, RARE

    public SeismicSlash() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
        baseSilly = silly = 3;
        baseDamage = 6;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new DrawCardAction(3));
    }

    @Override
    public void trance(AbstractMonster m) {
        applyToSelf(new EnergizedBluePower(AbstractDungeon.player, -silly));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSilly(-1);
    }
}