/*
 * Copyright (C) 2011 Jason von Nieda <jason@vonnieda.org>
 * 
 * This file is part of OpenPnP.
 * 
 * OpenPnP is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * OpenPnP is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with OpenPnP. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 * For more information about OpenPnP visit http://openpnp.org
 */

package org.openpnp.machine.reference.wizards;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openpnp.gui.support.AbstractConfigurationWizard;
import org.openpnp.gui.support.ActuatorsComboBoxModel;
import org.openpnp.gui.support.IntegerConverter;
import org.openpnp.gui.support.LengthConverter;
import org.openpnp.machine.reference.ReferenceNozzle;
import org.openpnp.model.AbstractModelObject;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class ReferenceNozzlePartDetectionWizard extends AbstractConfigurationWizard {
    private final ReferenceNozzle nozzle;
    private JLabel label;
    private JPanel panel;
    private JComboBox comboBoxActuator;

    public ReferenceNozzlePartDetectionWizard(ReferenceNozzle nozzle) {
        this.nozzle = nozzle;
        createUi();
    }
    private void createUi() {
        
        CellConstraints cc = new CellConstraints();
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        panel = new JPanel();
        contentPanel.add(panel);
        panel.setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.DEFAULT_COLSPEC,
                ColumnSpec.decode("default:grow"),},
            new RowSpec[] {
                FormSpecs.RELATED_GAP_ROWSPEC,
                RowSpec.decode("26px"),}));
        
        label = new JLabel("Vacuum Actuator");
        panel.add(label, "1, 2, right, center");
        
        comboBoxActuator = new JComboBox();
        comboBoxActuator.setModel(new ActuatorsComboBoxModel((AbstractModelObject)nozzle.getHead()));
        panel.add(comboBoxActuator, "2, 2");
    }

    @Override
    public void createBindings() {
        LengthConverter lengthConverter = new LengthConverter();
        IntegerConverter intConverter = new IntegerConverter();

        addWrappedBinding(nozzle, "vacuumSenseActuatorName", comboBoxActuator, "selectedItem");
    }
}
