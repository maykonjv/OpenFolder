package org.mvaz.openfolder;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "System",
        id = "org.mvaz.openfolder.OpenFolderAction"
)
@ActionRegistration(
        iconBase = "org/mvaz/openfolder/folder.png",
        displayName = "#CTL_OpenFolderAction"
)

@ActionReferences({
    @ActionReference(path = "Menu/File", position = 2700, separatorBefore = 2650, separatorAfter = 2750),
    @ActionReference(path = "Toolbars/File", position = 500),
    @ActionReference(path = "Loaders/folder/any/Actions", position = 0),
    @ActionReference(path = "Projects/Actions", position = 0),
    @ActionReference(path = "Projects/Actions", position = 5000),
    @ActionReference(path = "Loaders/text/x-java/Actions", position = 0),})
@Messages("CTL_OpenFolderAction=Open folder")
public final class OpenFolderAction implements ActionListener {

    private final DataObject context;

    public OpenFolderAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            if (context != null && context.getFolder() != null
                    && context.getPrimaryFile() != null
                    && context.getPrimaryFile().getPath() != null
                    && !context.getPrimaryFile().getPath().isEmpty()
                    && context.getFolder().getPrimaryFile() != null
                    && context.getFolder().getPrimaryFile().getPath() != null
                    && !context.getFolder().getPrimaryFile().getPath().isEmpty()
                    && Desktop.isDesktopSupported()) {

                if (context.getPrimaryFile().isFolder()) {
                    Desktop.getDesktop().open(new File(context.getPrimaryFile().getPath()));
                } else {
                    Desktop.getDesktop().open(new File(context.getFolder().getPrimaryFile().getPath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
