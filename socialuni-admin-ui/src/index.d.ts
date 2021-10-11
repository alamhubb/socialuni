import mx from '@/plugins/mxgraph'

declare global {
    interface Window {
        mxGraph: typeof mx.mxGraph;
        mxGraphModel: typeof mx.mxGraphModel;
        mxEditor: typeof mx.mxEditor;
        mxGeometry: typeof mx.mxGeometry;
        mxDefaultKeyHandler: typeof mx.mxDefaultKeyHandler;
        mxDefaultPopupMenu: typeof mx.mxDefaultPopupMenu;
        mxStylesheet: typeof mx.mxStylesheet;
        mxDefaultToolbar: typeof mx.mxDefaultToolbar;
    }
}
