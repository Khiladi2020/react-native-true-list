import { codegenNativeComponent, type ViewProps } from 'react-native';

interface NativeProps extends ViewProps {
  items?: ReadonlyArray<string>;
  itemTemplate?: string;
  dataFitter?: string;
}

export default codegenNativeComponent<NativeProps>('TrueListView');
