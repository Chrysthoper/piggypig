package Actividades;

import com.chryscontreras.micochinito.TransaccionFragment1;
import com.chryscontreras.micochinito.TransaccionFragment2;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.layouts.BasicWizardLayout;

/**
 * Created by Chrys-Emcor on 25/08/2016.
 */
public class TransaccionesWizard extends BasicWizardLayout {

    public TransaccionesWizard() {
        super();
    }

    //You must override this method and create a wizard flow by
    //using WizardFlow.Builder as shown in this example
    @Override
    public WizardFlow onSetup() {
        /* Optionally, you can set different labels for the control buttons
        setNextButtonLabel("Advance");
        setBackButtonLabel("Return");
        setFinishButtonLabel("Finalize"); */

        return new WizardFlow.Builder()
                .addStep(TransaccionFragment1.class)           //Add your steps in the order you want them
                .addStep(TransaccionFragment2.class)           //to appear and eventually call create()
                .create();                              //to create the wizard flow.
    }
}
