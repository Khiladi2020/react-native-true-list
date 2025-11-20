import { codegenNativeComponent, type ViewProps } from 'react-native';

export interface NativeProps extends ViewProps {
  items?: ReadonlyArray<string>;
  itemTemplate?: string;
  dataFitter?: string;
}

export default codegenNativeComponent<NativeProps>('TrueListView');
