/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.filesearch;

import org.sleuthkit.autopsy.datamodel.ImageNode;
import org.sleuthkit.autopsy.datamodel.VolumeNode;
import org.sleuthkit.autopsy.datamodel.FileNode;
import org.sleuthkit.autopsy.datamodel.DirectoryNode;
import javax.swing.Action;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.sleuthkit.autopsy.datamodel.ContentUtils;
import org.sleuthkit.autopsy.directorytree.ChangeViewAction;
import org.sleuthkit.datamodel.Content;

/**
 * This class wraps nodes as they are passed to the DataResult viewers.  It 
 * defines the actions that the node should have. 
 */
public class DataResultFilterNode extends FilterNode {

    private Node currentNode;

    /** the constructor */
    public DataResultFilterNode(Node arg) {
        super(arg, new DataResultFilterChildren(arg));
        this.currentNode = arg;
    }

    /**
     * Right click action for the nodes that we want to pass to the directory
     * table and the output view.
     *
     * @param popup
     * @return actions
     */
    @Override
    public Action[] getActions(boolean popup) {
        // right click action(s) for image node
        if (this.currentNode instanceof ImageNode) {
            return new Action[]{};
        } // right click action(s) for volume node
        else if (this.currentNode instanceof VolumeNode) {
            return new Action[]{};
        } // right click action(s) for directory node
        else if (this.currentNode instanceof DirectoryNode) {
            return new Action[]{
                        new ChangeViewAction("View", 0, currentNode),
                        new OpenParentFolderAction("Open Parent Directory", ContentUtils.getSystemPath(currentNode.getLookup().lookup(Content.class)))
                    };
        } // right click action(s) for the file node
        else if (this.currentNode instanceof FileNode) {
            return new Action[]{
                        // TODO: ContentNode fix - reimplement ExtractAction
                        // new ExtractAction("Extract", (FileNode) this.currentNode),
                        new ChangeViewAction("View", 0, currentNode),
                        new OpenParentFolderAction("Open Parent Directory", ContentUtils.getSystemPath(currentNode.getLookup().lookup(Content.class)))
                    };
        } else {
            return new Action[]{};
        }
    }

    /**
     * Double click action for the nodes that we want to pass to the directory
     * table and the output view.
     *
     * @return action
     */
    @Override
    public Action getPreferredAction() {
        return null;
    }
}
