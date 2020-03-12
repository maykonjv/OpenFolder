/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvaz.openfolder;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "System",
        id = "org.mvaz.openfolder.ProjectOpenAction"
)
@ActionRegistration(
        iconBase = "org/mvaz/openfolder/folder.png",
        displayName = "#CTL_ProjectOpenAction"
)
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = 600),
//    @ActionReference(path = "Loaders/folder/any/Actions", position = -100, separatorBefore = -150)
})
@Messages("CTL_ProjectOpenAction=Open Project")
public final class ProjectOpenAction implements ActionListener {

    private final Project context;

    public ProjectOpenAction(Project context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            if (context != null && context.getProjectDirectory() != null
                    && context.getProjectDirectory() != null
                    && Desktop.isDesktopSupported()) {

                if (context.getProjectDirectory().isFolder()) {
                    Desktop.getDesktop().open(new File(context.getProjectDirectory().getPath()));
                } else {
                    Desktop.getDesktop().open(new File(context.getProjectDirectory().getParent().getPath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
