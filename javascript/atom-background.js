
//credit:https://qiita.com/Ted-HM/items/afe9eddaefc0247859a8
// atom File->stylesheet... ->styles.less
@backgournd-image: url('C:/Users/umihico/GitHub/atom_wallpapers/atom_wallpaper.jpg') no-repeat fixed right top rgba(40,44,52,0.2);

.tree-view {
  background: @backgournd-image;
}

atom-text-editor {
  background: @backgournd-image;
}

atom-workspace-axis {
  background: @backgournd-image;
  .pane {
    opacity:1;
  }
  .active {
    opacity: 1;
  }
}
